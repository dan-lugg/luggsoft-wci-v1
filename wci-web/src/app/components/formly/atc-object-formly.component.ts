import {Component} from '@angular/core';
import {FieldType} from '@ngx-formly/core';

@Component({
    selector: 'atcObjectFormly',
    template: `
    <ng-container *ngIf="this.field.fieldGroup?.length; else emptyBlock">
      <div class="card my-3">
        <div class="card-header">
          <strong>{{ this.props.label || "object" }}</strong>
        </div>
        <div class="card-body">
          <p *ngIf="props.description">{{ this.props.description }}</p>
          <div class="alert alert-danger" *ngIf="showError && formControl.errors">
            <formly-validation-message [field]="this.field"></formly-validation-message>
          </div>
          <formly-field *ngFor="let field of this.field.fieldGroup" [field]="field"></formly-field>
        </div>
      </div>
    </ng-container>
    <ng-template #emptyBlock>
        <ngb-alert type="info" [dismissible]="false">There are no required parameters for this command.</ngb-alert>
    </ng-template>
  `
})
export class AtcObjectFormlyComponent extends FieldType {

}
