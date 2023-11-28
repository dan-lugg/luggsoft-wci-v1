import {ProcessState} from "./process-state";

export interface ProcessDescriptor
{
    processId: string;
    processState: ProcessState;
}
