import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";

import {ProcessDescriptor} from "../../services/commands/process-descriptor";
import {DefaultCommandDispatcher} from "../../services/commands/default-command-dispatcher.service";

@Component({
    template: `
        <div class="row mt-3">
            <div class="col">
                <h2>
                    <span>Detail Process</span>
                </h2>
                <h3>
                    <code *ngIf="this.processDescriptor">{{this.processDescriptor.processId}}</code>
                    <span class="badge bg-primary ms-2">
                        <span *ngIf="this.processDescriptor">{{this.processDescriptor.processState}}</span>
                    </span>
                </h3>
            </div>
        </div>
        
        <div class="row mt-3">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" ngbNav [(activeId)]="this.activeTabId" #nav="ngbNav">
                            <li [ngbNavItem]="1">
                                <a ngbNavLink>
                                    <span class="fa fa-terminal me-2"></span>
                                    <span>Process Log</span>
                                </a>
                                <ng-template>
                                    <div *ngIf="this.processDescriptor">
                                        <atcNotificationLog [sourceId]="this.processDescriptor.processId"></atcNotificationLog>
                                    </div>
                                </ng-template>
                            </li>
                            <li [ngbNavItem]="2">
                                <a ngbNavLink>
                                    <span class="fa fa-code me-2"></span>
                                    <span>Process Model</span>
                                </a>
                                <ng-template ngbNavContent>
                                    <pre *ngIf="this.processDescriptor">{{this.processDescriptor | json}}</pre>
                                </ng-template>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="my-2" [ngbNavOutlet]="this.nav"></div>
                    </div>
                </div>
                
                <div *ngIf="this.processDescriptor">
                    <atcNotificationLog [sourceId]="this.processDescriptor.processId"></atcNotificationLog>
                </div>
            
            </div>
        </div>
    `
})
export class AtcProcessDetailComponent implements OnInit
{
    activeTabId: number = 1;
    processDescriptor!: ProcessDescriptor;

    constructor(
        private activatedRoute: ActivatedRoute,
        private commandDispatcher: DefaultCommandDispatcher,
    )
    {
    }

    ngOnInit(): void
    {
        this.activatedRoute.params.subscribe(params =>
        {
            this.commandDispatcher.dispatchSelectProcessDescriptors()
                .subscribe(result =>
                {
                    this.processDescriptor = ([...result.processDescriptors] as ProcessDescriptor[])
                        .filter(processDescriptor => processDescriptor.processId == params["id"])
                        .pop() as ProcessDescriptor;
                });
        });
    }
}
