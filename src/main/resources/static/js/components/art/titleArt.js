function createRainbotArt() {
    const rainbotArt = `
██████╗  █████╗ ██╗███╗   ██╗██████╗  ██████╗ ████████╗
██╔══██╗██╔══██╗██║████╗  ██║██╔══██╗██╔═══██╗╚══██╔══╝
██████╔╝███████║██║██╔██╗ ██║██████╔╝██║   ██║   ██║   
██╔══██╗██╔══██║██║██║╚██╗██║██╔══██╗██║   ██║   ██║   
██║  ██║██║  ██║██║██║ ╚████║██████╔╝╚██████╔╝   ██║   
╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝    ╚═╝   
`;

    // Get the container element from the HTML.
    // Make sure you have a <div id="rainbot-container"></div> in your HTML.
    const container = document.getElementById('rainbot-title-container');

    // If the container doesn't exist, stop the script to avoid errors.
    if (!container) {
        console.error('Error: Element with ID "rainbot-title-container" was not found.');
        return;
    }

    // Create a <pre> element to hold the art.
    const preElement = document.createElement('pre');
    preElement.id = 'rainbot-ascii'; // Assign an ID for styling.
    preElement.textContent = rainbotArt; // Set its content to our art.

    // Add the new element to the container.
    container.appendChild(preElement);

    /*
     * OPTIONAL: Interactive Mouse Effect
     * This code makes the 3D effect react to the mouse position.
     */
    container.addEventListener('mousemove', (e) => {
        const { clientX, clientY } = e;
        const { offsetWidth, offsetHeight } = preElement;
        const { left, top } = preElement.getBoundingClientRect();
        
        // Calculate mouse position relative to the element's center.
        const x = (clientX - left - offsetWidth / 2) / 25;
        const y = (clientY - top - offsetHeight / 2) / 25;

        // Apply a 3D rotation transform based on mouse position.
        preElement.style.transform = `rotateY(${x}deg) rotateX(${-y}deg)`;
    });

    // Reset the transform when the mouse leaves the element.
    container.addEventListener('mouseleave', () => {
        preElement.style.transform = 'rotateY(0deg) rotateX(0deg)';
    });
}

// Export the function as default
export default createRainbotArt;

// Also run automatically if loaded directly (for backward compatibility)
if (typeof document !== 'undefined') {
    document.addEventListener('DOMContentLoaded', createRainbotArt);
}