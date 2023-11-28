import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: "atcBreadcrumb",
    template: `
        <div class="card">
            <div class="card-body">
                <ol class="breadcrumb mb-0">
                    <li class="breadcrumb-item" *ngFor="let breadcrumb of this.breadcrumbs">
                        <a [href]="breadcrumb.link">
                            <span>{{breadcrumb.name}}</span>
                        </a>
                    </li>
                </ol>
            </div>
        </div>
    `,
})
export class AtcBreadcrumbComponent implements OnInit
{
    breadcrumbs: { name: string, link: string }[] = [
        {name: "Foo", link: "https://example.com/foo"},
        {name: "Bar", link: "https://example.com/bar"},
        {name: "Qux", link: "https://example.com/qux"},
    ];

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
    )
    {
    }

    ngOnInit(): void
    {
    }
}
