FROM node:alpine as node
WORKDIR /app
COPY . .
RUN npm install --legacy-peer-deps
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node /app/dist/demo-app /usr/share/nginx/html
