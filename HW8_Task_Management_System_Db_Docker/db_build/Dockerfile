FROM postgres:latest
ENV POSTGRES_DB: task_manager_db
ENV POSTGRES_USER: postgres
ENV POSTGRES_PASSWORD: 08080808
COPY ../src/main/resources/db-scripts/creating_tables.sql /docker-entrypoint-initdb.d
#CMD ["echo", "Hello DB"]