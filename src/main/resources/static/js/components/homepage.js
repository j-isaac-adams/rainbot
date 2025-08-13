// Homepage Component
class HomepageComponent {
    constructor() {
        this.state = {
            title: 'Welcome to Rainbot',
            description: 'Your intelligent weather companion',
            features: [
                'Real-time weather updates',
                'Smart weather predictions',
                'Personalized recommendations',
                'Beautiful, intuitive interface'
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
        
        setTimeout(() => this.bindEvents(), 0);
        
        return container;
    }

    getTemplate() {
        return `
            <div class="homepage">
                <header class="homepage-header">
                    <h1 class="main-title">${this.state.title}</h1>
                    <p class="main-description">${this.state.description}</p>
                    <nav class="main-navigation">
                        <a href="/about" class="nav-link">About</a>
                    </nav>
                </header>
                
                <main class="homepage-main">
                    <section class="features-section">
                        <h2>Features</h2>
                        <div class="features-grid">
                            ${this.state.features.map(feature => `
                                <div class="feature-card">
                                    <div class="feature-icon">🌤️</div>
                                    <h3>${feature}</h3>
                                </div>
                            `).join('')}
                        </div>
                    </section>
                    
                    <section class="cta-section">
                        <h2>Get Started</h2>
                        <p>Experience the future of weather forecasting</p>
                        <button class="cta-button" id="get-started-btn">
                            Launch Rainbot
                        </button>
                    </section>
                </main>
                
                <footer class="homepage-footer">
                    <p>&copy; 2024 Rainbot. All rights reserved.</p>
                </footer>
            </div>
        `;
    }

    bindEvents() {
        const getStartedBtn = document.getElementById('get-started-btn');
        if (getStartedBtn) {
            getStartedBtn.addEventListener('click', () => this.handleGetStarted());
        }

        const navLinks = document.querySelectorAll('.nav-link');
        navLinks.forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                const href = link.getAttribute('href');
                if (window.rainbotApp) {
                    window.rainbotApp.navigateTo(href);
                }
            });
        });
    }

    handleGetStarted() {
        // Random test button.
    }

    destroy() {
        // Cleanup any event listeners or resources
    }
}

export default HomepageComponent;
