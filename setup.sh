#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(pwd)"
MONEROPAY_REPO="https://gitlab.com/moneropay/moneropay.git"
MONERO_RPC_REPO="https://github.com/sethforprivacy/simple-monero-wallet-rpc-docker.git"

gen_secret() {
  openssl rand -hex 32
}

gen_password() {
  openssl rand -hex 16
}

fill_if_empty() {
  local key="$1"
  local value="$2"

  if grep -q "^${key}=$" .env; then
    sed -i "s|^${key}=$|${key}=${value}|" .env
  elif ! grep -q "^${key}=" .env; then
    echo "${key}=${value}" >> .env
  fi
}

require_command() {
  command -v "$1" >/dev/null 2>&1 || {
    echo "Missing required command: $1"
    exit 1
  }
}

require_command git
require_command docker
require_command openssl

if ! docker compose version >/dev/null 2>&1; then
  echo "docker compose is required"
  exit 1
fi

[ -f .env ] || cp .env.example .env

if [ ! -d "$ROOT_DIR/moneropay" ]; then
  git clone "$MONEROPAY_REPO"
fi

if [ ! -d "$ROOT_DIR/simple-monero-wallet-rpc-docker" ]; then
  git clone "$MONERO_RPC_REPO"
fi

# ------------------------
# Backend database
# ------------------------
fill_if_empty "DB_HOST" "backend-db"
fill_if_empty "DB_USER" "moneromerchant"
fill_if_empty "DB_PASSWORD" "$(gen_password)"
fill_if_empty "DB_NAME" "moneromerchant"
fill_if_empty "DB_PORT" "5432"

# ------------------------
# MoneroPay database
# ------------------------
fill_if_empty "MONEROPAY_POSTGRES_USERNAME" "moneropay"
fill_if_empty "MONEROPAY_POSTGRES_PASSWORD" "$(gen_password)"
fill_if_empty "MONEROPAY_POSTGRES_DATABASE" "moneropay"

# ------------------------
# Backend admin
# ------------------------
fill_if_empty "ADMIN_NAME" "admin"
fill_if_empty "ADMIN_PASSWORD" "$(gen_password)"

# ------------------------
# Backend JWT secrets
# ------------------------
fill_if_empty "JWT_SECRET" "$(gen_secret)"
fill_if_empty "JWT_REFRESH_SECRET" "$(gen_secret)"
fill_if_empty "JWT_MONEROPAY_SECRET" "$(gen_secret)"
fill_if_empty "JWT_LWS_TOKEN" "$(gen_secret)"

# ------------------------
# Wallet
# ------------------------
fill_if_empty "WALLET_PASSWORD" "$(gen_password)"

echo
echo "Setup complete."
echo
echo "Review .env and make sure these are correct for your environment:"
echo "  - MONERO_DAEMON_RPC_HOSTNAME"
echo "  - MONERO_DAEMON_RPC_PORT"
echo "  - MONERO_DAEMON_RPC_USERNAME / PASSWORD"
echo "  - MONERO_DAEMON_RPC_ENDPOINT"
echo "  - MONERO_WALLET_RPC_ENDPOINT"
echo "  - MONERO_WALLET_RPC_USERNAME / PASSWORD"
echo "  - MONEROPAY_CALLBACK_URL"
echo
echo "Then validate your compose file with:"
echo "  docker compose config"
echo
echo "Then start everything with:"
echo "  docker compose up -d --build"
