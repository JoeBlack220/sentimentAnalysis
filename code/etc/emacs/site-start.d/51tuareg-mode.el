(add-to-list 'auto-mode-alist '("\\.ml[iylp]?\\'" . tuareg-mode))
(autoload 'tuareg-mode "tuareg" "Major mode for editing Caml code" t)
(autoload 'camldebug "ocamldebug-tuareg" "Run the Caml debugger" t)
(dolist (ext '(".cmo" ".cmx" ".cma" ".cmxa" ".cmi"))
  (add-to-list 'completion-ignored-extensions ext))
(debian-pkg-add-load-path-item
 (concat "/usr/share/"
	 (symbol-name debian-emacs-flavor)
	 "/site-lisp/tuareg-mode"))
;; SMIE turned off due to upstream bug #1167
(setq tuareg-use-smie nil)




