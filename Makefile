.PHONY: install up down logs ps restart config pull build rebuild reset clean help

install:
	./setup.sh
	docker compose up -d --build

up:
	docker compose up -d

build:
	docker compose build

rebuild:
	docker compose up -d --build

down:
	docker compose down

logs:
	docker compose logs -f

ps:
	docker compose ps

restart:
	docker compose down
	docker compose up -d

config:
	docker compose config

pull:
	docker compose pull

reset:
	docker compose down -v --remove-orphans
	rm -f .env
	sudo rm -rf moneropay
	sudo rm -rf simple-monero-wallet-rpc-docker

clean:
	docker compose down -v --remove-orphans
	docker builder prune -f

help:
	@echo "Available targets:"
	@echo "  make install   - run setup.sh and build/start everything"
	@echo "  make up        - start existing containers"
	@echo "  make build     - build images only"
	@echo "  make rebuild   - rebuild images and start containers"
	@echo "  make down      - stop containers"
	@echo "  make logs      - follow container logs"
	@echo "  make ps        - show container status"
	@echo "  make restart   - restart containers"
	@echo "  make config    - show resolved docker compose config"
	@echo "  make pull      - pull remote images"
	@echo "  make reset     - remove containers, volumes, .env, and cloned deps"
	@echo "  make clean     - remove containers, volumes, and prune builder cache"