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
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <span class="fa fa-cube me-2"></span>
                                <span>{{commandRequestDescriptor.title}}</span>
                            </div>
                            <div>
                                <span class="badge bg-primary me-2" *ngIf="this.isSystemCommand(commandRequestDescriptor)">
                                    <span class="fa fa-server me-2"></span>
                                    <span>System</span>
                                </span>
                                <span class="badge bg-info me-2" *ngIf="this.isClientCommand(commandRequestDescriptor)">
                                    <span class="fa fa-user me-2"></span>
                                    <span>Client</span>
                                </span>
                                <span class="badge bg-warning me-2" *ngIf="this.isAsyncCommand(commandRequestDescriptor)">
                                    <span class="fa fa-clock-o me-2"></span>
                                    <span>Async</span>
                                </span>
                                <span class="badge bg-primary me-2" *ngIf="this.isAwaitCommand(commandRequestDescriptor)">
                                    <span class="fa fa-bolt me-2"></span>
                                    <span>Await</span>
                                </span>
                            </div>
                        </div>
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

    isSystemCommand(commandRequestDescriptor: CommandRequestDescriptor): boolean
    {
        return commandRequestDescriptor.commandDefinitionOrigin == "SYSTEM";
    }

    isClientCommand(commandRequestDescriptor: CommandRequestDescriptor): boolean
    {
        return commandRequestDescriptor.commandDefinitionOrigin == "CLIENT";
    }

    isAsyncCommand(commandRequestDescriptor: CommandRequestDescriptor): boolean
    {
        return commandRequestDescriptor.commandExecutionType == "ASYNC";
    }

    isAwaitCommand(commandRequestDescriptor: CommandRequestDescriptor): boolean
    {
        return commandRequestDescriptor.commandExecutionType == "AWAIT";
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
