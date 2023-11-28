import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnDestroy, OnInit} from "@angular/core";
import {CommandRequestDescriptor} from "../../services/commands/command-request-descriptor";
import {DefaultCommandDispatcher} from "../../services/commands/default-command-dispatcher.service";

@Component({
    template: `
        <div class="row">
            <div class="col">
                <h2>Command Selector</h2>
            </div>
            <div class="col d-flex justify-content-end">
                <input class="form-control m-1" type="search" />
                <button class="btn btn-outline-info m-1" (click)="this.load()">
                    <span class="fa fa-refresh"></span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="card my-3" *ngFor="let commandRequestDescriptor of this.commandRequestDescriptors">
                    <div class="card-header">
                        <span class="badge bg-primary ms-3" *ngIf="commandRequestDescriptor.commandExecutionType === 'SYSTEM'">
                            <span>System Command</span>
                        </span>
                        <span class="badge bg-warning ms-3" *ngIf="commandRequestDescriptor.commandExecutionType === 'CLIENT'">
                            <span>Client Command</span>
                        </span>
                        <span>{{commandRequestDescriptor.title}}</span>
                    </div>
                    <div class="card-body">
                        <div class="d-flex flex-row justify-content-between align-items-center">
                            <div>
                                <p>
                                    <span>{{commandRequestDescriptor.description}}</span>
                                </p>
                                <div>
                                    <code class="me-2">{{commandRequestDescriptor.commandRequestClass}}</code>
                                </div>
                            </div>
                            <div>
                                <a class="btn btn-outline-info btn-sm m-1" href>
                                    <span>Detail</span>
                                </a>
                                <a class="btn btn-outline-primary btn-sm m-1"
                                   [routerLink]="['..', 'invoke', commandRequestDescriptor.commandRequestClass]" href>
                                    <span>Invoke</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,
    changeDetection: ChangeDetectionStrategy.Default,
})
export class AtcCommandSelectComponent implements OnInit, OnDestroy
{
    commandRequestDescriptors: CommandRequestDescriptor[] = [];

    constructor(
        private changeDetectorRef: ChangeDetectorRef,
        private commandDispatcher: DefaultCommandDispatcher,
    )
    {
    }

    ngOnInit()
    {
        this.load();
    }

    ngOnDestroy()
    {
        console.log("Destroying!");
    }

    load()
    {
        this.commandDispatcher.dispatchSelectCommandRequestDescriptors()
            .subscribe((successResult: any) =>
            {
                this.commandRequestDescriptors = [...(successResult.commandRequestDescriptors)];
            });
    }
}

export interface SelectCommandRequestDescriptorsCommandResult
{
    ["$type"]: string;
    commandRequestDescriptors: CommandRequestDescriptor[];
}
