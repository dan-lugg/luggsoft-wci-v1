import {Interval} from '../interval';

export interface NotificationIntervalFactory {
    createNotificationInterval(sourceId: string, notificationsHandler: (notifications: any[]) => void): Interval;
}
