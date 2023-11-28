import {AfterViewInit, Component, ElementRef, Input, QueryList, ViewChildren} from "@angular/core";
import {Notification} from "../services/notifications/notification";
import {NotificationLevel} from "../services/notifications/notificationLevel";

@Component({
    selector: "atcNotificationList",
    template: `
        <div class="card">
            <div class="card-body bg-dark">
                <table class="table table-dark table-borderless table-sm mb-0">
                    <tbody>
                        <tr tabindex="1" *ngFor="let logMessage of this.notifications" #notificationList>
                            <td style="width: 1%;">
                                <div [style]="{ color: this.getTextColor(logMessage.level) }"
                                     class="fa {{this.getIconClass(logMessage.level)}}"></div>
                            </td>
                            <td style="width: 1%;">
                                <pre [style]="{ color: this.getTextColor(logMessage.level) }"
                                     style="white-space: nowrap;">{{logMessage.time}}</pre>
                            </td>
                            <td>
                                <pre [style]="{ color: this.getTextColor(logMessage.level) }"
                                     style="white-space: pre-wrap;">{{this.getFormattedMessage(logMessage)}}</pre>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="card-header">
                <button class="btn btn-info" (click)="this.enableAutoscroll = !this.enableAutoscroll">
                    <span>Autoscroll {{this.enableAutoscroll ? "On" : "Off"}}</span>
                </button>
            </div>
        </div>
    `,
})
export class AtcNotificationList implements AfterViewInit
{
    @Input()
    notifications!: Notification[];

    @ViewChildren("notificationList")
    notificationListElement!: QueryList<ElementRef>;

    enableAutoscroll: boolean = true;

    ngAfterViewInit(): void
    {
        this.notificationListElement.changes.subscribe(element =>
        {
            window.setTimeout(() =>
            {
                if (this.enableAutoscroll)
                {
                    element.last.nativeElement.focus();
                }
            });
        });
    }

    getFormattedMessage(logMessage: Notification): string
    {
        if (typeof logMessage.payload == "string")
        {
            return logMessage.payload;
        }

        return JSON.stringify(logMessage.payload, null, 2);
    }

    getTextColor(notificationLevel: NotificationLevel): string
    {
        switch (notificationLevel)
        {
            case NotificationLevel.GENERAL:
                return "#fff";
            case NotificationLevel.WARNING:
                return "#ff6";
            case NotificationLevel.FAILURE:
                return "#f66";
            case NotificationLevel.SUCCESS:
                return "#3f6";
        }
    }

    getIconClass(logType: NotificationLevel): string
    {
        switch (logType)
        {
            case NotificationLevel.GENERAL:
                return "fa-circle";
            case NotificationLevel.WARNING:
                return "fa-exclamation-circle";
            case NotificationLevel.FAILURE:
                return "fa-times-circle";
            case NotificationLevel.SUCCESS:
                return "fa-check-circle";
        }
    }
}
