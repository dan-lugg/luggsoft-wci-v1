import {Injectable} from '@angular/core';
import {Interval} from './interval';

@Injectable()
export class IntervalFactory {
    createInterval(timeout: number, handler: () => void): Interval {
        return new Interval(window.setInterval(handler, timeout));
    }
}

