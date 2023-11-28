import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {SystemCommandRequestTypes} from "./system-command-request-types";
import {CommandResult} from "./command-result";
import {CommandRequest} from "./command-request";
import {CommandDispatcherBase} from "./command-dispatcher-base";

@Injectable()
export class DefaultCommandDispatcher extends CommandDispatcherBase<CommandRequest<CommandResult>, CommandResult>
{

    constructor(
        httpClient: HttpClient,
    )
    {
        super(httpClient);
    }

    dispatchSelectSystemProperties(): Observable<any>
    {
        return this.dispatchCommand({$type: SystemCommandRequestTypes.SELECT_SYSTEM_PROPERTIES_TYPE});
    }

    dispatchSelectCommandRequestDescriptors(): Observable<any>
    {
        return this.dispatchCommand({$type: SystemCommandRequestTypes.SELECT_COMMAND_REQUEST_DESCRIPTORS_TYPE});
    }

    /**
     * @deprecated This probably no longer functions.
     */
    dispatchSelectAsyncCommandProcessDescriptors(): Observable<any>
    {
        return this.dispatchCommand({$type: SystemCommandRequestTypes.SELECT_ASYNC_COMMAND_PROCESS_DESCRIPTORS_TYPE});
    }

    dispatchSelectProcessDescriptors(): Observable<any>
    {
        return this.dispatchCommand({$type: SystemCommandRequestTypes.SELECT_PROCESS_DESCRIPTORS_TYPE});
    }
}
