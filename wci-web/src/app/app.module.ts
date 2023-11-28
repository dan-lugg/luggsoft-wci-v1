import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";

import {FormlyModule} from "@ngx-formly/core";
import {ReactiveFormsModule} from "@angular/forms";
import {FormlyBootstrapModule} from "@ngx-formly/bootstrap";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";
import {AtcHomeComponent} from "./pages/atc-home.component";
import {AtcCommandComponent} from "./pages/command/atc-command.component";
import {AtcCommandSelectComponent} from "./pages/command/atc-command-select.component";
import {AtcCommandInvokeComponent} from "./pages/command/atc-command-invoke.component";
import {AtcObjectFormlyComponent} from "./components/formly/atc-object-formly.component";
import {AtcArrayFormlyComponent} from "./components/formly/atc-array-formly.component";
import {AtcProcessComponent} from "./pages/process/atc-process.component";
import {AtcProcessSelectComponent} from "./pages/process/atc-process-select.component";
import {AtcProcessDetailComponent} from "./pages/process/atc-process-detail.component";
import {AtcNotificationLog} from "./components/atc-notification-log.component";
import {DefaultNotificationIntervalFactory} from "./services/notifications/default-notification-interval.factory";
import {AtcCommandFormComponent} from "./components/atc-command-form.component";
import {AtcCommandItem} from "./components/atc-command-item.component";
import {AtcCommandList} from "./components/atc-command-list.component";
import {AtcFooterComponent} from "./components/atc-footer.component";
import {AtcNavbarComponent} from "./components/atc-navbar.component";
import {AtcAppComponent} from "./components/atc-app.component";
import {DefaultCommandDispatcher} from "./services/commands/default-command-dispatcher.service";
import {IntervalFactory} from "./services/interval-factory.service";
import {AtcBreadcrumbComponent} from "./components/atc-breadcrumb.component";
import {AtcNotificationList} from "./components/atc-notification-list.component";
// import {AtcCommandOriginBadgeComponent} from "./pages/command/atc-command-origin-badge.component"
// import {NgxJsonViewerModule} from "ngx-json-viewer";

const routes: Routes = [
    {
        path: "",
        component: AtcHomeComponent
    },
    {
        path: "command",
        component: AtcCommandComponent,
        children: [
            {
                path: "",
                redirectTo: "select",
                pathMatch: "full",
            },
            {
                path: "select",
                component: AtcCommandSelectComponent
            },
            {
                path: "invoke/:id",
                component: AtcCommandInvokeComponent
            },
        ]
    },
    {
        path: "process",
        component: AtcProcessComponent,
        children: [
            {
                path: "",
                redirectTo: "select",
                pathMatch: "full",
            },
            {
                path: "select",
                component: AtcProcessSelectComponent
            },
            {
                path: "detail/:id",
                component: AtcProcessDetailComponent
            },
        ]
    }
];

@NgModule({
    declarations: [
        AtcAppComponent,
        AtcArrayFormlyComponent,
        AtcCommandItem,
        AtcCommandList,
        AtcFooterComponent,
        AtcNavbarComponent,
        AtcObjectFormlyComponent,
        AtcCommandComponent,
        AtcCommandFormComponent,
        AtcHomeComponent,
        AtcCommandInvokeComponent,
        AtcCommandSelectComponent,
        AtcProcessComponent,
        AtcProcessDetailComponent,
        AtcProcessSelectComponent,
        AtcNotificationLog,
        AtcBreadcrumbComponent,
        AtcNotificationList,
        // AtcCommandOriginBadgeComponent,
    ],
    imports: [
        // NgxJsonViewerModule,
        BrowserModule,
        FormlyBootstrapModule,
        HttpClientModule,
        NgbModule,
        ReactiveFormsModule,
        FormlyModule.forRoot({
            types: [
                {name: "array", component: AtcArrayFormlyComponent},
                {name: "object", component: AtcObjectFormlyComponent},
            ]
        }),
        // TODO: Re-enable useHash when you can get it working
        RouterModule.forRoot(routes /*, {useHash: true} */),
    ],
    providers: [
        IntervalFactory,
        DefaultCommandDispatcher,
        DefaultNotificationIntervalFactory,
    ],
    bootstrap: [AtcAppComponent]
})
export class AppModule
{
}
