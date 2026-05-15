# Monero Merchant
**Free and Open Source Monero Point of Sale (POS)**

<img width="500" src="https://github.com/user-attachments/assets/ac8414eb-4d28-4609-a4fe-4144e17d46f7" alt="Monero Merchant screenshot" />
<br>
<a href="https://xmrpos.twed.org/fdroid" target="_blank" rel="noopener noreferrer">
  <img width="200" height="77" alt="fdroid" src="https://github.com/user-attachments/assets/125217c5-1bfb-4ba3-85e4-dc69ab78637b" />
</a>

---

**Monero Merchant** is a FOSS Android point-of-sale (POS) system for accepting Monero (XMR) payments. It provides a **self-hosted**, **trustless**, and **secure** payment solution for vendors and merchants.

---

## Features
- **Open Source:** Licensed under GNU GPL v3.0.  
- **Trustless Architecture:** Operate your own backend; no reliance on third parties.  
- **Device-Agnostic:** Works on any Android device — no proprietary hardware.  
- **Receipt Printing:** Supports Bluetooth ESC/POS printers.  
- **Scalable:** Unlimited POS clients and vendors, centrally managed.  
- **Secure:** No wallet keys stored or exposed on client devices.  
- **Integrated Backend:** Backend API interfaces with `monerod` and **MoneroPay**.  
- **Fast Payments:** Supports 0-confirmation Monero transactions in 5–10 seconds.

---

## Compatibility
- **Client:** Any Android device, with or without a Bluetooth mobile printer.  
- **Backend:** Ubuntu LTS VPS or LAN environment (low-spec compatible).  

---

## Taking Payments

1. Enter the amount in your `primaryFiatCurrency` and tap the **green button**.
2. The app generates a Monero payment QR or NFC tag with address, amount, and note prefilled.
   The customer scans it and sends funds using any Monero wallet.
3. Once the payment reaches the configured confirmation threshold (0, 1, or 10),
   the app automatically advances to the receipt screen where you can:

   * Print a receipt
   * Start a new order

---

## Settings Overview

### Company Information

* Upload a logo (appears on receipts)
* Edit company name and contact details
* Customize footer text

### Fiat Currencies

* Configure the `primaryFiatCurrency`
* Add multiple `referenceFiatCurrencies`

### Security

* Enable PIN protection for app startup or settings access
* **Note:** PINs cannot currently be reset — choose carefully

### Printer Settings

* Select connection type (Bluetooth tested and supported)
* Adjust printer parameters if needed
* Use **Test Print** to verify output

---

## Building Monero Merchant Client from Source

### Prerequisites

* [Android Studio](https://developer.android.com/studio)

### 1. Clone the Repository

```bash
git clone https://github.com/Monero-Merchant/monero-merchant
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select **Open an Existing Project**
3. Navigate to `monero-merchant/pos` and open it

### 3. Install Dependencies

Android Studio auto-installs dependencies.
If not, manually sync Gradle:

```
File > Sync Project with Gradle Files
```

### 4. Choose Build Variant

```
View > Tool Windows > Build Variants
```

Select `debug` or `release`.

### 5. Build the APK

#### GUI method:

```
Build > Build APK(s)
```

#### Command line:

```bash
./gradlew assembleDebug    # Debug build
./gradlew assembleRelease  # Release build
```

Output: `app/build/outputs/apk/`

---

## Building with Docker

### Requirements

* Docker Engine ≥ 24
* Docker Compose plugin
* 8 GB RAM, ~10 GB free disk space
* User added to `docker` group

### Build

```bash
git clone https://github.com/Monero-Merchant/monero-merchant
cd monero-merchant/pos
docker compose build --no-cache
docker compose up --abort-on-container-exit
```

### Using Prebuilt Image

```bash
git clone https://github.com/Monero-Merchant/monero-merchant
cd monero-merchant/pos
docker run --rm \
  -v "$PWD":/workspace \
  -v monero-merchant-gradle:/home/gradle-cache \
  -v monero-merchant-android-sdk:/opt/android-sdk \
  ghcr.io/ajs-xmr/xmrpos-android-builder:df7af4d
```

Output APK: `app/build/outputs/apk/debug/app-debug.apk`

---

## Backend Setup

### Prerequisites
- Clean Ubuntu LTS VPS
- Docker (user added to docker group)
- Docker Compose
- Git
- OpenSSL

### Install

`git clone https://github.com/Monero-Merchant/monero-merchant`

`cd monero-merchant`

`make install`

### Start / Restart / Rebuild / Stop

`make up` # start services

`make restart` # restart containers (after .env changes)

`make rebuild` # rebuild images and restart

`make down` # stop containers

### Status & Logs

`make ps` # show running containers

`make logs` # stream logs

### Cleanup / Uninstall (Warning: this will delete database and wallet data)

`make clean` # remove containers, volumes, and build cache

`make reset` # full reset: clean + delete .env and local dependencies

---

## Umbrel Install

### Prerequisites
- [Umbrel](https://umbrel.com) OS running on a Raspberry Pi or Linux server
- Monero daemon (`monerod`) and wallet RPC running (or accessible remotely)
- [MoneroPay](https://github.com/MoneroPay/MoneroPay) running (or accessible remotely)

### Method 1 — Community App Store (Recommended)

1. Open your Umbrel dashboard → **App Store**
2. Add community app store:
   ```
   https://github.com/brainchainz/umbrel-app-store
   ```
3. Search for **Monero Merchant** → **Install**
4. Fill in the configuration:
   - Admin password
   - Monero daemon RPC endpoint
   - Monero wallet RPC endpoint
   - MoneroPay base URL
5. Click **Install** — the app will auto-generate secrets and start
6. Access the admin dashboard at the app's Umbrel URL

### Method 2 — Manual Sideload

```bash
# On your Umbrel host
mkdir -p ~/umbrel/app-data/monero-merchant
cd ~/umbrel/app-data/monero-merchant

# Download the compose and config files
curl -o docker-compose.yml https://raw.githubusercontent.com/brainchainz/monero-merchant/main/umbrel/docker-compose.yml
curl -o exports.sh https://raw.githubusercontent.com/brainchainz/monero-merchant/main/umbrel/exports.sh
curl -o umbrel-app.yml https://raw.githubusercontent.com/brainchainz/monero-merchant/main/umbrel/umbrel-app.yml
chmod +x exports.sh

# Generate .env and .secrets
APP_DATA_DIR=$(pwd) APP_ADMIN_PASSWORD=changeme bash exports.sh

# Start services
APP_DATA_DIR=$(pwd) docker compose up -d
```

Access the dashboard at `http://<your-umbrel-ip>:8080`.

> For detailed backend API usage, see [`backend/README.md`](backend/README.md).

---

## Donations

Support the project with Monero (XMR):

```
88zkpYQRJPmeuycSN7Jx3UHq9vH1u2dD8eE1rECvCAouPj75Cdnu1eUacQ5p7ZMvdr4e6BRe2FShv4HoatSs9HcwEeZCupZ
```

---

## License

Licensed under the **GNU General Public License v3.0**.
See [LICENSE](LICENSE) for details.
