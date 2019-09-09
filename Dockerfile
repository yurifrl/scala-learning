FROM hseeberger/scala-sbt:8u212_1.2.8_2.13.0
WORKDIR /app
# Cache dependencies first
COPY project project
COPY build.sbt .
RUN sbt update
# Then build
COPY ./src ./src
# RUN sbt compile
