class RainbotApp {
    constructor() {
        this.appContainer = document.getElementById('app');
        this.currentComponent = null;
        this.loadedComponents = new Map();
        this.routes = {
            '/': 'homepage',
            '/about': 'about',
        };
        
        this.init();
    }

    init() {
        window.rainbotApp = this;
        
        this.handleRoute();
        
        window.addEventListener('popstate', () => {
            this.handleRoute();
        });
        
        document.addEventListener('click', (e) => {
            if (e.target.matches('[data-route]')) {
                e.preventDefault();
                const route = e.target.getAttribute('data-route');
                this.navigateTo(route);
            }
        });
    }

    handleRoute() {
        const path = window.location.pathname;
        const componentName = this.routes[path] || this.routes['/'];
        this.loadAndRenderComponent(componentName);
    }

    navigateTo(path) {
        window.history.pushState({}, '', path);
        this.handleRoute();
    }

    async loadAndRenderComponent(componentName) {
        try {
            
            const componentClass = await this.loadComponent(componentName);
            if (componentClass) {
                this.loadedComponents.set(componentName, componentClass);
                this.renderComponent(new componentClass());
            } else {
                console.error('Component not found:', componentName);
                this.showError(`Component '${componentName}' not found`);
            }
        } catch (error) {
            console.error(`Error loading component '${componentName}':`, error);
            this.showError(`Failed to load ${componentName} component`);
        }
    }

    async loadComponent(componentName) {
        try {
            const module = await import(`/js/components/${componentName}.js`);
            return module.default || module[`${this.capitalizeFirst(componentName)}Component`] || module[componentName];
        } catch (error) {
            console.error(`Error loading component '${componentName}':`, error);
            return null;
        }
    }

    capitalizeFirst(str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    }

    renderComponent(component) {
        if (this.currentComponent && this.currentComponent.destroy) {
            this.currentComponent.destroy();
        }
        
        this.currentComponent = component;
        this.appContainer.innerHTML = '';
        
        if (component.render) {
            const element = component.render();
            if (element) {
                this.appContainer.appendChild(element);
            }
        }
    }

    showError(message) {
        this.appContainer.innerHTML = `
            <div style="padding: 20px; color: red; text-align: center;">
                <h2>Error</h2>
                <p>${message}</p>
                <button onclick="window.location.reload()">Reload Page</button>
            </div>
        `;
    }

    addRoute(path, componentName) {
        this.routes[path] = componentName;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new RainbotApp();
});
