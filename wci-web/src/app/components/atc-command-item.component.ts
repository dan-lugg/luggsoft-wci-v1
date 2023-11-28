import {Component, Input} from '@angular/core';

@Component({
  selector: '[atc-command-item]',
  template: `
    <pre class="mb-0">{{this.commandItem | json}}</pre>
  `
})
export class AtcCommandItem {
  @Input()
  commandItem!: object;
}
