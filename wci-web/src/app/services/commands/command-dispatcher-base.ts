import {CommandRequest} from "./command-request";
import {CommandResult} from "./command-result";
import {CommandDispatcher} from "./command-dispatcher";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

export abstract class CommandDispatcherBase<TRequest extends CommandRequest<TResult>, TResult extends CommandResult> implements CommandDispatcher<TRequest, TResult>
{
    protected constructor(
        private httpClient: HttpClient,
    )
    {
    }

    dispatchCommand(request: TRequest): Observable<TResult>
    {
        try
        {
            return this.httpClient
                .post("/api/command", request, {
                    params: (new HttpParams())
                        .set("type", request.$type)
                })
                .pipe(map(result => result as TResult));
        }
        catch (error)
        {
            console.error(error);
            throw error;
        }
    }
}
