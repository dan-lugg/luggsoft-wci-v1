import {Component} from '@angular/core';

@Component({
  template: `
      <div class="row">
          <div class="col">
              <div class="my-5 text-center">
                  <h1 class="display-5 fw-bold text-body-emphasis mb-1">Welcome To WCI</h1>
                  <h2 class="display-5 fw-bold text-body-emphasis mb-1">
                      <code>wci-example</code>
                  </h2>
                  <div class="col-lg-6 mx-auto">
                      <p class="lead mb-1">
                          <span>Welcome to WCI, the Web Command Interface task platform.</span>
                      </p>
                      <p class="mb-1">
                          <span>Here, you can perform administrative tasks by invoking commands, see previously executed command results, and many other things.</span>
                      </p>
                      <hr class="m-3" />
                      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                          <button type="button" class="btn btn-outline-primary btn-lg px-4 gap-3">
                              <span>See Commands</span>
                          </button>
                          <button type="button" class="btn btn-outline-info btn-lg px-4 gap-3">
                              <span>See Processes</span>
                          </button>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  `
})
export class AtcHomeComponent {

}
