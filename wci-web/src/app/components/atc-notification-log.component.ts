import {AfterViewInit, Component, Input, OnChanges, OnDestroy, SimpleChanges} from "@angular/core";

import {DefaultNotificationIntervalFactory} from "../services/notifications/default-notification-interval.factory";
import {Notification} from "../services/notifications/notification";
import * as _ from "lodash";
import {NotificationLevel} from "../services/notifications/notificationLevel";
import {DateTime} from "luxon";

import {Interval} from "../services/interval";

@Component({
    selector: "atcNotificationLog",
    template: `
        <atcNotificationList [notifications]="this.notifications"></atcNotificationList>
    `,
})
export class AtcNotificationLog implements OnChanges, OnDestroy
{
    notifications: Notification[] = [];

    private notificationInterval!: Interval;

    @Input()
    set sourceId(sourceId: string)
    {
        if (sourceId !== null)
        {
            this.notificationInterval = this.notificationIntervalFactory.createNotificationInterval(this.sourceId, (notifications: any[]) =>
            {
                this.notifications = [
                    ..._.map(notifications, notification =>
                    {
                        return {
                            level: notification.level as NotificationLevel,
                            time: DateTime.fromISO(notification.at),
                            payload: notification.payload,
                            sourceId: notification.sourceId,
                            sourceName: notification.sourceName,
                        };
                    })
                ];
            });
        }
    }

    constructor(
        private notificationIntervalFactory: DefaultNotificationIntervalFactory,
    )
    {
    }

    ngOnChanges(changes: SimpleChanges): void
    {
        changes["sourceId"].currentValue;
        console.log(changes);
    }

    ngOnDestroy(): void
    {
        this.notificationInterval.clear();
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
