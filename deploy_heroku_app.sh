#!/bin/bash

sudo docker build -f multistage.Dockerfile -t registry.heroku.com/$1/web .

sudo docker push registry.heroku.com/$1/web

sudo heroku container:release web --app $1

sudo heroku logs --tail --app $1
