import {CommandRequestDescriptor} from '../../services/commands/command-request-descriptor';

export interface SelectCommandRequestDescriptorsCommandResult
{
    ["$type"]: string;
    commandRequestDescriptors: CommandRequestDescriptor[];
}
