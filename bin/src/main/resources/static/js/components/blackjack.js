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

        setTimeout(() => {
            this.loadArtContent();
        }, 0);
        
        return container;
    }

    getTemplate() {
        return `
            <div class="blackjack-container">
                <a href="/" class="back-button">&lt;</a>

                <div id="blackjack-title-container"></div>
                <h1 class="coming-soon"> Coming Soon! </h1>
            </div>
        `;
    }

    async loadArtContent() {
        try {
            const artModule = await import('/js/components/art/blackjackArt.js');
            
            if (typeof artModule.default === 'function') {
                artModule.default();
            }
        } catch (error) {
            console.error('Failed to load art.js:', error);
        }
    }

    destroy() {
        console.log('Blackjack component destroyed');
    }
}

export default BlackjackComponent;
