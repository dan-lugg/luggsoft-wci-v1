import {ChangeDetectionStrategy, Component, OnInit} from "@angular/core";
import {DefaultCommandDispatcher} from "../../services/commands/default-command-dispatcher.service";

@Component({
    template: `
        <h2>Select Process</h2>
        <div class="card" *ngFor="let processDescriptor of this.processDescriptors">
            <div class="card-header">
                <span>{{processDescriptor.processId}}</span>
                <span>{{processDescriptor.processState}}</span>
            </div>
            <div class="card-body">
                <a class="btn btn-outline-primary" href [routerLink]="['..', 'detail', processDescriptor.processId]">
                    <span>Detail</span>
                </a>
            </div>
        </div>
    `,
    changeDetection: ChangeDetectionStrategy.Default,
})
export class AtcProcessSelectComponent implements OnInit
{

    processDescriptors!: ProcessDescriptor[];

    constructor(private commandDispatcher: DefaultCommandDispatcher)
    {
    }

    ngOnInit()
    {
        this.commandDispatcher.dispatchSelectProcessDescriptors()
            .subscribe((successResult: any) =>
            {
                console.log(successResult);
                this.processDescriptors = [...(<[]>successResult.processDescriptors)];
                console.info({message: "Loaded", processDescriptors: this.processDescriptors});
            });
    }
}

interface ProcessDescriptor
{
    processId: string;
    processState: ProcessState;

    [property: string]: unknown;
}

type ProcessState = "RUNNING" | "STOPPED";
