import {Component, Input} from '@angular/core';

@Component({
  selector: '[atc-command-list]',
  template: `
    <div class="card mb-3" *ngFor="let commandItem of this.commandList">
      <div class="card-body" atc-command-item [commandItem]="commandItem"></div>
    </div>
  `
})
export class AtcCommandList {
  @Input()
  commandList!: object[];
}
