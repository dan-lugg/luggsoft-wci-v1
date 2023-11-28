import {SystemCommandResultTypes} from "./system-command-result-types";

export interface CommandResult
{
    $type: (string | SystemCommandResultTypes);

    [property: string]: unknown;
}
