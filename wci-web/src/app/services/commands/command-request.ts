import {CommandResult} from './command-result';
import {SystemCommandRequestTypes} from './system-command-request-types';

export interface CommandRequest<TResult extends CommandResult> {
    $type: (string | SystemCommandRequestTypes);

    [property: string]: unknown;
}
