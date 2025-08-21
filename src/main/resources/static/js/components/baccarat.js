class BaccaratComponent {
    constructor() {
        this.data = {
        };
        
        this.init();
    }

    init() {}

    render() {
        const container = document.createElement('div');
        container.className = 'baccarat-container';
        container.innerHTML = this.getTemplate();
        
        setTimeout(() => {
            this.loadArtContent();
        }, 0);

        return container;
    }

    getTemplate() {
        return `
            <div class="baccarat-container">
                <a href="/" class="back-button">&lt;</a>
                <div id="baccarat-title-container"></div>
                <input type="text" class="transparent-input" placeholder="WebSocket">
            </div>
        `;
    }

    async loadArtContent() {
        try {
            const artModule = await import('/js/components/art/baccaratArt.js');
            
            if (typeof artModule.default === 'function') {
                artModule.default();
            }
        } catch (error) {
            console.error('Failed to load art.js:', error);
        }
    }

    destroy() {
        console.log('Baccarat component destroyed');
    }
}

export default BaccaratComponent;
