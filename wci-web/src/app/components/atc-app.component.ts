import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DefaultCommandDispatcher} from "../services/commands/default-command-dispatcher.service";
import {IntervalFactory} from "../services/interval-factory.service";
import {Interval} from "../services/interval";
import _ from "lodash";
import {Duration} from "luxon";
import {SelectSystemPropertiesResult} from "./select-system-properties.result";

@Component({
    selector: "[atc-app-component]",
    template: `
        
        <header atcNavbar
                [systemProperties]="this.systemProperties">
        </header>
        
        <main class="my-3 flex-shrink-0">
            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <atcBreadcrumb></atcBreadcrumb>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <router-outlet></router-outlet>
                    </div>
                </div>
            </div>
            
            <!--
            <div class="container-fluid">
              <div class="row">
                <div class="col">
                  <atcNotificationLog [sourceId]="this.notificationSourceId"></atcNotificationLog>
                </div>
              </div>
            </div>
            -->
        
        </main>
        
        <footer class="footer mt-auto py-3 bg-primary-subtle" atcFooter
                [systemProperties]="this.systemProperties">
        </footer>
    `,
})
export class AtcAppComponent implements OnInit
{
    commandSchemas: { [key: string]: object } = {};

    notificationSourceId: string = "00000000-0000-0000-0000-000000000000";

    systemProperties!: SelectSystemPropertiesResult;

    private systemPropertiesInterval!: Interval;

    constructor(
        private httpClient: HttpClient,
        private intervalFactory: IntervalFactory,
        private commandDispatcher: DefaultCommandDispatcher,
    )
    {
    }

    ngOnInit(): void
    {
        // TODO: De-duplicate this logic, you idiot
        this.commandDispatcher.dispatchSelectSystemProperties()
            .subscribe(commandResult =>
            {
                this.systemProperties = {
                    instanceName: commandResult.instanceName,
                    serverVersion: _.join([
                            `${commandResult.serverVersion.major}.`,
                            `${commandResult.serverVersion.minor}.`,
                            `${commandResult.serverVersion.patch}-`,
                            `${commandResult.serverVersion.build}`,
                        ],
                        "",
                    ),
                    uptimeDuration: Duration.fromISO(commandResult.uptimeDuration).toFormat("dd:hh:mm:ss"),
                };
            });

        this.systemPropertiesInterval = this.intervalFactory.createInterval(5_000, () =>
        {
            this.commandDispatcher.dispatchSelectSystemProperties()
                .subscribe(commandResult =>
                {
                    this.systemProperties = {
                        instanceName: commandResult.instanceName,
                        serverVersion: _.join([
                                `${commandResult.serverVersion.major}.`,
                                `${commandResult.serverVersion.minor}.`,
                                `${commandResult.serverVersion.patch}-`,
                                `${commandResult.serverVersion.build}`,
                            ],
                            "",
                        ),
                        uptimeDuration: Duration.fromISO(commandResult.uptimeDuration).toFormat("dd:hh:mm:ss"),
                    };
                });
        });
    }
}

