import {CommandExecutionType} from "./command-execution-type";
import {CommandOriginType} from "./command-origin-type";

export interface CommandRequestDescriptor
{
    title: string;
    description: string;
    commandRequestClass: string;
    commandRequestSchema: object;
    commandExecutionType: CommandExecutionType;
    commandDefinitionOrigin: CommandOriginType;
}

