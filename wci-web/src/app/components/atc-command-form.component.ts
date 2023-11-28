import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {FormGroup} from "@angular/forms";
import {FormlyFieldConfig} from "@ngx-formly/core";
import {FormlyJsonschema} from "@ngx-formly/core/json-schema";
import {CommandRequest} from "../services/commands/command-request";

@Component({
    selector: "atcCommandForm",
    template: `
        <form [formGroup]="this.form">
            <formly-form
                [form]="this.form"
                [fields]="this.fields"
                [(model)]="this.commandRequest"
                (modelChange)="this.onSubmit($event)">
            </formly-form>
        </form>
    `,
})
export class AtcCommandFormComponent implements OnInit
{
    form: FormGroup = new FormGroup({});
    fields: FormlyFieldConfig[] = [];

    @Input()
    commandRequest!: CommandRequest<any>;

    @Input()
    commandRequestType!: string;

    @Input()
    commandRequestSchema!: object;

    @Output()
    onCommandRequestChange: EventEmitter<object> = new EventEmitter<object>();

    constructor(
        private formlyJsonschema: FormlyJsonschema,
    )
    {
    }

    onSubmit(model: any)
    {
        model["$type"] = this.commandRequestType;
        this.onCommandRequestChange.emit(model);
    }

    ngOnInit(): void
    {
        this.fields = [this.formlyJsonschema.toFieldConfig(this.commandRequestSchema)];

        // TODO: Bug in this logic, it uses {} for default arrays, instead of []
        /*
        const defaultCommandRequest = jsonDefault(this.commandRequestSchema);
        console.log({defaultCommandRequest: defaultCommandRequest});
        this.commandRequest = _.merge({}, defaultCommandRequest, {
            $type: this.commandRequestType,
        });
        */

        /*
        this.commandRequest = _.merge({}, {
            $type: this.commandRequestType,
        });
        */
    }
}
