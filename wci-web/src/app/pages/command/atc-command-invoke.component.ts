import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {CommandRequestDescriptor} from "../../services/commands/command-request-descriptor";
import {DefaultCommandDispatcher} from "../../services/commands/default-command-dispatcher.service";
import {CommandRequest} from "../../services/commands/command-request";
import _ from "lodash";
import {Subscription} from "rxjs";
import {CommandResult} from "../../services/commands/command-result";
import {CommandExecutionType} from "../../services/commands/command-execution-type";

@Component({
    template: `
        <div class="row mt-3">
            <div class="col">
                <h2>
                    <span>Command Invoker</span>
                </h2>
                <h3 *ngIf="this.commandRequestDescriptor">
                    <span>{{this.commandRequestDescriptor.title}}</span>
                </h3>
                <p *ngIf="this.commandRequestDescriptor">
                    <span>{{this.commandRequestDescriptor.description}}</span>
                </p>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" ngbNav [(activeId)]="this.activeTabId" #nav="ngbNav">
                            <li [ngbNavItem]="1">
                                <a ngbNavLink>
                                    <span class="fa fa-file me-2"></span>
                                    <span>Command Request Form</span>
                                </a>
                                <ng-template ngbNavContent>
                                    <atcCommandForm *ngIf="this.commandRequestDescriptor"
                                                    [commandRequest]="this.commandRequest"
                                                    [commandRequestType]="this.commandRequestDescriptor.commandRequestClass"
                                                    [commandRequestSchema]="this.commandRequestDescriptor.commandRequestSchema"
                                                    (onCommandRequestChange)="this.handleCommandRequestChange($event)">
                                    </atcCommandForm>
                                </ng-template>
                            </li>
                            <li [ngbNavItem]="2">
                                <a ngbNavLink>
                                    <span class="fa fa-code me-2"></span>
                                    <span>Command Request Model</span>
                                </a>
                                <ng-template ngbNavContent>
                                    <pre class="mb-0">{{this.commandRequest | json}}</pre>
                                </ng-template>
                            </li>
                            <li [ngbNavItem]="3">
                                <a ngbNavLink>
                                    <span class="fa fa-code me-2"></span>
                                    <span>Command Request Schema</span>
                                </a>
                                <ng-template ngbNavContent>
                                    <pre class="mb-0">{{this.commandRequestDescriptor.commandRequestSchema | json}}</pre>
                                </ng-template>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="my-2" [ngbNavOutlet]="this.nav"></div>
                    </div>
                    <div class="card-footer">
                        <button type="button" class="btn btn-outline-success" (click)="this.validate()">
                            <span>{{this.submissionButtonMessage}}</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-3" *ngIf="this.commandResult">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        <span>Result</span>
                    </div>
                    <div class="card-body">
                        <pre>{{this.commandResult | json}}</pre>
                        <!--
                        <ngx-json-viewer [json]="this.commandResult" [expanded]="true"></ngx-json-viewer>
                        -->
                    </div>
                </div>
            </div>
        </div>
    `
})
export class AtcCommandInvokeComponent implements OnInit
{
    activeTabId: number = 1;

    commandRequest!: CommandRequest<any>;
    commandRequestDescriptor!: CommandRequestDescriptor;
    commandResult!: CommandResult;

    get submissionButtonMessage(): string
    {
        switch (this.commandRequestDescriptor.commandExecutionType as CommandExecutionType)
        {
            case "ASYNC":
            {
                return "Start Process";
            }
            case "AWAIT":
            {
                return "Execute Command";
            }
        }
    }


    constructor(
        private activatedRoute: ActivatedRoute,
        private commandDispatcher: DefaultCommandDispatcher,
    )
    {
    }

    load(): void
    {

    }

    validate(): Subscription
    {
        const commandRequest = _.merge({}, this.commandRequest, {
            $type: this.commandRequestDescriptor.commandRequestClass,
        });

        console.log(commandRequest);

        switch (this.commandRequestDescriptor.commandExecutionType)
        {
            case "AWAIT":
            {
                console.log({awaitRequest: commandRequest});

                return this.commandDispatcher.dispatchCommand(commandRequest)
                    .subscribe(result =>
                    {
                        this.commandResult = result;
                        console.log({awaitResult: result});
                    });
            }
            case "ASYNC":
            {
                console.log({asyncRequest: commandRequest});

                return this.commandDispatcher.dispatchCommand(commandRequest)
                    .subscribe(result =>
                    {
                        this.commandResult = result;
                        console.log({asyncResult: result});
                    });
            }
        }
    }

    handleCommandRequestChange(commandRequest: object): void
    {
        this.commandRequest = commandRequest as CommandRequest<any>;
        console.log(this.commandRequest);
    }

    ngOnInit(): void
    {
        this.activatedRoute.params.subscribe(params =>
        {
            this.commandDispatcher.dispatchSelectCommandRequestDescriptors()
                .subscribe((result: any) =>
                {
                    this.commandRequestDescriptor = ([...result.commandRequestDescriptors] as CommandRequestDescriptor[])
                        .filter(commandRequestDescriptor => commandRequestDescriptor.commandRequestClass == params["id"])
                        .pop() as CommandRequestDescriptor;
                });
        });
    }
}
