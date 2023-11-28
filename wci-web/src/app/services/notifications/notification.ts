import {NotificationLevel} from './notificationLevel';
import {DateTime} from 'luxon';

export interface Notification {
  time: DateTime;
  payload: any;
  sourceId: string;
  sourceName: string;
  level: NotificationLevel;
}
