import {Component, Input} from "@angular/core";
import {SelectSystemPropertiesResult} from "./select-system-properties.result";

@Component({
    selector: "[atcFooter]",
    template: `
        <footer class="footer mt-auto py-3">
            <div class="container text-body-secondary text-center">
                <code class="m-1">{{this.instanceName}}</code>
                <code class="m-1">{{this.serverVersion}}</code>
                <code class="m-1">{{this.uptimeDuration }}</code>
            </div>
        </footer>
    `,
})
export class AtcFooterComponent
{
    @Input()
    systemProperties!: SelectSystemPropertiesResult;

    get instanceName(): string
    {
        return this.systemProperties?.instanceName || "instanceName";
    }

    get serverVersion(): string
    {
        return this.systemProperties?.serverVersion || "serverVersion";
    }

    get uptimeDuration(): string
    {
        return this.systemProperties?.uptimeDuration || "uptimeDuration";
    }

    constructor()
    {
    }
}
