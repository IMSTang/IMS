{{/*
Expand the name of the chart.
*/}}
{{- define "ims.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "ims.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "ims.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "ims.labels" -}}
helm.sh/chart: {{ include "ims.chart" . }}
{{ include "ims.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "ims.selectorLabels" -}}
app.kubernetes.io/name: {{ include "ims.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "ims.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "ims.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Return ims configMap name
*/}}
{{- define "ims.configMap.name" -}}
{{- printf "%s-%s" .Chart.Name .Values.configMap.name | trunc 63 | trimSuffix "-"  | quote }}
{{- end }}


{{/*
ims selector
*/}}
{{- define "ims.selector" -}}
app: {{ template "ims.name" . }}
{{- end -}}

{{/*
mysql headless svc hostname
*/}}
{{- define "mysql.host" -}}
{{- $Values := dict "Values" .Values.mysql "Chart" (dict "Name" "mysql") "Release" .Release -}}
{{ template "mysql.hs.name" $Values }}
{{- end -}}
