FROM node:current-slim

WORKDIR /usr/src/app

COPY package.json .

RUN npm install

EXPOSE 3000

CMD ["node", "app.js"]

COPY . .