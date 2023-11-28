import {Component} from '@angular/core';
import {FieldArrayType} from '@ngx-formly/core';

@Component({
    selector: 'atcArrayFormly',
    template: `
        <div class="card my-3">
            <div class="card-header">
                <strong>{{ this.props.label || "array" }}</strong>
            </div>
            <div class="card-body">
                <p *ngIf="props.description">{{ this.props.description }}</p>
                
                
                <!--
                <div class="alert alert-danger" *ngIf="showError && formControl.errors">
                    <formly-validation-message [field]="this.field"></formly-validation-message>
                </div>
                -->
                
                <div class="d-flex flex-row" *ngFor="let field of this.field.fieldGroup; let index = index">
                    <div class="flex-fill">
                        <formly-field class="col" [field]="field"></formly-field>
                    </div>
                    <!--
                    <div *ngIf="f.props.removable !== false" class="col-2 text-right">
                    -->
                    <div class="align-self-start">
                        <button class="mt-0 ms-3 btn btn-outline-danger" type="button" (click)="this.remove(index)">
                            <span class="fa fa-remove" ngbTooltip="Remove this item" [openDelay]="500"></span>
                        </button>
                    </div>
                    <!--
                    </div>
                    -->
                </div>
                <div class="text-end">
                    <button class="btn btn-outline-primary" type="button" (click)="this.add()">
                        <span class="fa fa-plus" ngbTooltip="Add new item &hellip;" [openDelay]="500"></span>
                    </button>
                </div>
            </div>
        </div>
    `,
})
export class AtcArrayFormlyComponent extends FieldArrayType {
}
