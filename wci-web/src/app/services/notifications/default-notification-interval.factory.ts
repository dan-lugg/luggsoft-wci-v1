import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationIntervalFactory} from './notification-interval.factory';

import {IntervalFactory} from '../interval-factory.service';
import {Interval} from '../interval';

@Injectable()
export class DefaultNotificationIntervalFactory implements NotificationIntervalFactory {

    private delay: number = 3000;

    constructor(
        // private delay: number,
        private httpClient: HttpClient,
        private intervalFactory: IntervalFactory,
    ) {
    }

    createNotificationInterval(sourceId: string, notificationsHandler: (notifications: any[]) => void): Interval {
        const startInstant = new Date(0);
        const untilInstant = new Date(Date.now());
        return this.intervalFactory.createInterval(this.delay, () => {
            this.httpClient.post<any>('/api/command', this.createCommandRequest('00000000-0000-0000-0000-000000000000', startInstant, untilInstant))
                .subscribe(value => notificationsHandler(value.notifications));
        });
    }

    private createCommandRequest(sourceId: string, startInstant: Date, untilInstant: Date) {
        return {
            $type: 'com.luggsoft.wci.core.commands.standard.SelectNotificationsAwaitCommandRequest',
            sourceId: sourceId,
            startInstant: startInstant, // '0000-00-00T00:00:00Z',
            untilInstant: new Date(Date.now()), // '9999-99-99T00:00:00Z',
        };
    }
}
