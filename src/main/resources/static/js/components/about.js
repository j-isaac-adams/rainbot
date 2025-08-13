class AboutComponent {
    constructor() {
        this.state = {
            title: 'About Rainbot',
            description: 'Learn more about our mission and technology',
            content: [
                {
                    title: 'Our Mission',
                    text: 'To provide intelligent, accurate, and personalized weather forecasting that helps people make better decisions about their day.'
                },
                {
                    title: 'Technology',
                    text: 'Built with cutting-edge AI and machine learning algorithms, Rainbot processes vast amounts of weather data to deliver precise predictions.'
                },
                {
                    title: 'Team',
                    text: 'A passionate team of meteorologists, data scientists, and engineers dedicated to revolutionizing weather forecasting.'
                }
            ]
        };
        
        this.init();
    }

    init() {
        this.bindEvents();
    }

    bindEvents() {
        // Add event listeners for about page interactions
    }

    render() {
        const container = document.createElement('div');
        container.className = 'about-container';
        container.innerHTML = this.getTemplate();
        
        setTimeout(() => this.bindEvents(), 0);
        
        return container;
    }

    getTemplate() {
        return `
            <div class="about">
                <header class="about-header">
                    <h1 class="page-title">${this.state.title}</h1>
                    <p class="page-description">${this.state.description}</p>
                </header>
                
                <main class="about-main">
                    <div class="content-grid">
                        ${this.state.content.map(item => `
                            <div class="content-card">
                                <h2>${item.title}</h2>
                                <p>${item.text}</p>
                            </div>
                        `).join('')}
                    </div>
                    
                    <div class="navigation-section">
                        <button class="nav-button" data-route="/">Back to Home</button>
                    </div>
                </main>
            </div>
        `;
    }

    bindEvents() {
        const navButtons = document.querySelectorAll('[data-route]');
        navButtons.forEach(button => {
            button.addEventListener('click', (e) => {
                e.preventDefault();
                const route = e.target.getAttribute('data-route');
                if (window.rainbotApp) {
                    window.rainbotApp.navigateTo(route);
                }
            });
        });
    }

    destroy() {
        // Cleanup any event listeners or resources
    }
}

export default AboutComponent;
