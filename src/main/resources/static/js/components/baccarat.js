class BaccaratComponent {
    constructor() {
        this.data = {
        };
        
        this.init();
    }

    init() {
        this.handleEnterKey = this.handleEnterKey.bind(this);
    }

    render() {
        const container = document.createElement('div');
        container.className = 'baccarat-container';
        container.innerHTML = this.getTemplate();
        
        this.inputElement = container.querySelector('#websocket-input');

        if (this.inputElement) {
            this.inputElement.addEventListener('keydown', this.handleEnterKey);
        }

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
                <input type="text" id="websocket-input" class="transparent-input" placeholder="WebSocket">
            </div>
        `;
    }

    handleEnterKey(event) {
        if (event.key === 'Enter') {
            const uri = event.target.value.trim();
            console.log("Event");
            if (uri) {
                event.preventDefault(); 
                this.sendUriToServer(uri);
            }
        }
    }

    async sendUriToServer(uri) {
        const endpoint = '/gameSession_baccarat';
        const payload = {
            'uri' : uri
        };

        console.log(`Sending POST request to ${endpoint} with payload:`, payload);

        try {
            const response = await fetch(endpoint, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const result = await response.json();
            console.log('✅ Server response:', result);

        } catch (error) {
            console.error('❌ Failed to send request:', error);
        }
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
        if (this.inputElement) {
            this.inputElement.removeEventListener('keydown', this.handleEnterKey);
        }
        console.log('Baccarat component destroyed');
    }
}

export default BaccaratComponent;
