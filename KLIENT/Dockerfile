# Use the official Node.js image.
FROM node:18-alpine

# Set the working directory.
WORKDIR /app

# Copy the public directory to the working directory.
COPY public /app/public

# Install a simple HTTP server to serve the static files.
RUN npm install -g serve

# Expose the port the app runs on.
EXPOSE 3000

# Command to run the HTTP server.
CMD ["serve", "-s", "public", "-l", "3000"]
