class PokerComponent {
    constructor() {
        this.data = {
        };
        
        this.init();
    }

    init() {}

    render() {
        const container = document.createElement('div');
        container.className = 'poker-container';
        container.innerHTML = this.getTemplate();
        
        return container;
    }

    getTemplate() {
        return `
            <div class="poker-container">
                <div class="game-header">
                    <h1 class="game-title">Poker</h1>
                </div>
            </div>
        `;
    }

    destroy() {
        console.log('Poker component destroyed');
    }
}

export default PokerComponent;
