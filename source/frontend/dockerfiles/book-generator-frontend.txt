FROM sinsung6722/book-generator-frontend-base:v1


WORKDIR /root

COPY public/ public/
COPY src/ src/


EXPOSE 3000
ENTRYPOINT [ "npm", "start" ]
