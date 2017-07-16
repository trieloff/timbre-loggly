# timbre-loggly
A timbre (ClojureScript/Node.js) appender that posts to Loggly

[![Clojars Project](https://img.shields.io/clojars/v/trieloff/timbre-loggly.svg)](https://clojars.org/trieloff/timbre-loggly)
[![CircleCI](https://circleci.com/gh/trieloff/timbre-loggly.svg?style=shield)](https://circleci.com/gh/trieloff/timbre-loggly)

## Background

**timbre-loggly** is a log appender for the [τaoenssō/timbre](https://github.com/ptaoussanis/timbre) logging library that posts logs to the [Loggly.com](https://www.loggly.com) logging as a service provider. It is meant for a ClojureScript on Node.js environment.

## Installation

Add this to your `project.clj`'s `:dependencies` vector:

```clojure
[trieloff/timbre-loggly "0.1.0"]
```

You will also need an API Token from Loggly.

## Usage

Require **timbre** and **timbre-loggly**.

```clojure
(:require [taoensso.timbre :as timbre
    :refer-macros [log  trace  debug  info  warn  error  fatal  report
                  logf tracef debugf infof warnf errorf fatalf reportf
                  spy get-env]]
  [timbre.loggly :as loggly])
```

Set up the log appender

```clojure
(timbre/merge-config! {:appenders 
  {:loggly (loggly/loggly-appender 
    {:tags [:example  :timbre]}
     :token "<your-loggly-api-token>"})}}))
```

Log away

```clojure
(log "Check me out on loggly.com")
```

### Configuration Parameters

#### `:token`

(reqired) The Loggly API key

#### `:tags`

(optional) A list of Loggly tags that will make it easier to identify your application among multiple apps sharing the same Loggly token.

## License

Apache License 2.0

## Contributing

Contributions (in the form of pull requests) are very welcome. Following areas would be most interesting:

- extend support to browser runtimes
- extend support to Clojure