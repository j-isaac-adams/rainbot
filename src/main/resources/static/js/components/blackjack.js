class BlackjackComponent {
    constructor() {
        this.data = {
        };
        
        this.init();
    }

    init() {}

    render() {
        const container = document.createElement('div');
        container.className = 'blackjack-container';
        container.innerHTML = this.getTemplate();
        
        return container;
    }

    getTemplate() {
        return `
            <div class="blackjack-container">
                <div class="game-header">
                    <h1 class="game-title">Blackjack</h1>
                </div>
            </div>
        `;
    }

    destroy() {
        console.log('Blackjack component destroyed');
    }
}

export default BlackjackComponent;
