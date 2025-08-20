class HomepageComponent {
    constructor() {
        this.data = {
            games: [
                {
                    id: 'baccarat',
                    name: 'Baccarat'
                },
                {
                    id: 'blackjack',
                    name: 'Blackjack'
                },
                {
                    id: 'poker',
                    name: 'Poker'
                }
            ]
        };
        
        this.init();
    }

    init() {
        this.bindEvents();
    }

    render() {
        const container = document.createElement('div');
        container.className = 'homepage-container';
        container.innerHTML = this.getTemplate();
        
        setTimeout(() => {
            this.loadArtContent();
            this.bindEvents();
        }, 0);
        
        return container;
    }

    getTemplate() {
        return `
            <div class="homepage-container">
                <div id="rainbot-container"></div>
                <div class="games-grid">
                    ${this.data.games.map(game => `
                        <button class="play-button" data-route="${game.id}">
                            ${game.name}
                        </button>
                    `).join('')}
                </div>
            </div>
        `;
    }

    async loadArtContent() {
        try {
            const artModule = await import('/js/components/art.js');
            
            if (typeof artModule.default === 'function') {
                artModule.default();
            }
        } catch (error) {
            console.error('Failed to load art.js:', error);
            this.createFallbackArt();
        }
    }

    createFallbackArt() {
        const container = document.getElementById('rainbot-container');
        if (container) {
            const rainbotArt = `
██████╗  █████╗ ██╗███╗   ██╗██████╗  ██████╗ ████████╗
██╔══██╗██╔══██╗██║████╗  ██║██╔══██╗██╔═══██╗╚══██╔══╝
██████╔╝███████║██║██╔██╗ ██║██████╔╝██║   ██║   ██║   
██╔══██╗██╔══██╗██║██║╚██╗██║██╔══██╗██║   ██║   ██║   
██║  ██║██║  ██║██║██║ ╚████║██████╔╝╚██████╔╝   ██║   
╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝    ╚═╝   
`;
            const preElement = document.createElement('pre');
            preElement.id = 'rainbot-ascii';
            preElement.textContent = rainbotArt;
            container.appendChild(preElement);
        }
    }

    bindEvents() {
        const gameCards = document.querySelectorAll('.game-card');
        gameCards.forEach(card => {
            card.addEventListener('click', (e) => {
                const gameId = e.currentTarget.dataset.route;
                this.handleGameClick(gameId);
            });
        });

        const playButtons = document.querySelectorAll('.play-button');
        playButtons.forEach(button => {
            button.addEventListener('click', (e) => {
                e.stopPropagation();
                const gameId = e.currentTarget.dataset.route;
                this.handleGameClick(gameId);
            });
        });
    }

    handleGameClick(gameId) {
        console.log(`Game clicked: ${gameId}`);
        
        if (window.rainbotApp) {
            window.rainbotApp.navigateTo(`/${gameId}`);
        } else {
            alert(`${this.data.games.find(g => g.id === gameId).name} is coming soon!`);
        }
    }

    destroy() {
        console.log('Homepage component destroyed');
    }
}

export default HomepageComponent;
