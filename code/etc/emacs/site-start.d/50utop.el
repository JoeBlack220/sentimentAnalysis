;; -*-emacs-lisp-*-

(let ((package-dir (concat "/usr/share/"
                           (symbol-name debian-emacs-flavor)
                           "/site-lisp")))
;; If package-dir does not exist, the utop package must have
;; removed but not purged, and we should skip the setup.
  (when (file-directory-p package-dir)
    (if (fboundp 'debian-pkg-add-load-path-item)
        (debian-pkg-add-load-path-item package-dir)
      (setq load-path (cons package-dir load-path)))
    (autoload 'utop-mode "utop"
      "Set the buffer mode to utop." t)
    (autoload 'utop-minor-mode "utop"
      "Minor mode for utop." t)
    (autoload 'utop "utop"
      "A universal toplevel for OCaml." t)))

