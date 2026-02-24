-- V1__Create_contact_submissions.sql
-- Erstellt alle Tabellen automatisch beim ersten Start

-- Tabelle für Kontaktformulare
CREATE TABLE IF NOT EXISTS contact_submissions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (LENGTH(name) > 0),
    email VARCHAR(255) NOT NULL CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    address TEXT NOT NULL CHECK (LENGTH(TRIM(address)) > 0),
    museum TEXT NOT NULL CHECK (LENGTH(museum) > 0),
    employer VARCHAR(255) NOT NULL CHECK (LENGTH(employer) > 0),
    message TEXT,
    submission_date DATE NOT NULL DEFAULT CURRENT_DATE,
    signature TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
    );

-- Index für schnellere Abfragen
CREATE INDEX IF NOT EXISTS idx_contact_email ON contact_submissions(email);
CREATE INDEX IF NOT EXISTS idx_contact_date ON contact_submissions(created_at);

-- Trigger für updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_contact_submissions_updated_at
    BEFORE UPDATE ON contact_submissions
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Beispiel-Daten (optional)
INSERT INTO contact_submissions (name, email, address, museum, employer, signature, submission_date)
VALUES ('Test User', 'test@example.com', 'Teststraße 1', 'test museum', 'Test GmbH', 'data:image/png;base64,...', CURRENT_DATE)
    ON CONFLICT DO NOTHING;
