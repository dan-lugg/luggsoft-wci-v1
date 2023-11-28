import {CommandRequest} from './command-request';
import {CommandResult} from './command-result';
import {Observable} from 'rxjs';

export interface CommandDispatcher<TRequest extends CommandRequest<TResult>, TResult extends CommandResult> {
    dispatchCommand(request: TRequest): Observable<TResult>;
}
