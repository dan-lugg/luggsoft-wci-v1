import {Component, Input, OnInit} from "@angular/core";
import {SelectSystemPropertiesResult} from "./select-system-properties.result";

@Component({
    selector: "[atcNavbar]",
    template: `
        <nav class="navbar navbar-dark bg-primary border-bottom navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <!--
                    <img src="/assets/amrp_logo.svg" alt="ATC" class="me-2" />
                    -->
                    <span class="me-2 fa fa-bolt"></span>
                    <span class="me-2">WCI</span>
                    <span class="me-2">&bull;</span>
                    <span class="me-2">{{this.instanceName}}</span>
                </a>
                <button class="navbar-toggler" type="button">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" [routerLink]="['']" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}">
                                <span class="me-1 fa fa-home"></span>
                                <span>Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" [routerLink]="['command']" routerLinkActive="active">
                                <span class="me-1 fa fa-cubes"></span>
                                <span>Commands</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" [routerLink]="['process']" routerLinkActive="active">
                                <span class="me-1 fa fa-server"></span>
                                <span>Processes</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" [routerLink]="['status']" routerLinkActive="active">
                                <span class="me-1 fa fa-thermometer"></span>
                                <span>Status</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span class="me-1 fa fa-user"></span>
                                <span>[[USER]]</span>
                            </a>
                        </li>
                        <li class="nav-item dropdown" display="dynamic" ngbDropdown>
                            <a class="nav-link dropdown-toggle" href="#" ngbDropdownToggle>
                                <span class="me-1 fa fa-support"></span>
                                <span>Support</span>
                            </a>
                            <ul class="dropdown-menu" ngbDropdownMenu>
                                <a href ngbDropdownItem>
                                    <span>Action</span>
                                </a>
                                <a href ngbDropdownItem>
                                    <span>Action</span>
                                </a>
                                <a href ngbDropdownItem>
                                    <span>Action</span>
                                </a>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    `,
})
export class AtcNavbarComponent implements OnInit
{
    @Input()
    systemProperties!: SelectSystemPropertiesResult;

    get instanceName(): string
    {
        return this.systemProperties?.instanceName || "instanceName";
    }

    get serverVersion(): string
    {
        return this.systemProperties?.serverVersion || "serverVersion";
    }

    get uptimeDuration(): string
    {
        return this.systemProperties?.uptimeDuration || "uptimeDuration";
    }

    ngOnInit(): void
    {
    }
}
