import {Component, Input} from "@angular/core";
import {CommandOriginType} from "../../services/commands/command-origin-type";

@Component({
    selector: "atcCommandOriginBadge",
    template: `
        <ng-container [ngSwitch]="this.originType">
            <span class="badge bg-primary me-3" *ngSwitchCase="'SYSTEM'">
                <span class="fa fa-server me-2"></span>
                <span>System Command</span>
            </span>
            <span class="badge bg-info me-3" *ngSwitchCase="'CLIENT'">
                <span class="fa fa-user me-2"></span>
                <span>Client Command</span>
            </span>
            <span class="badge bg-danger me-3" *ngSwitchDefault>
                <span class="fa fa-question-circle me-2"></span>
                <span>Unknown Origin</span>
            </span>
        </ng-container>
    `
})
export class AtcCommandOriginBadgeComponent
{
    @Input()
    commandOriginType!: CommandOriginType;

    get originType(): string
    {
        return this.commandOriginType as string;
    }
}
