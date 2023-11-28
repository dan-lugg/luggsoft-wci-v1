export class Interval {
    constructor(
        private intervalId: number,
    ) {
    }

    clear(): void {
        window.clearInterval(this.intervalId);
    }
}
